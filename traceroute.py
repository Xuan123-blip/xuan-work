from socket import *
import os
import sys
import struct
import time
import select
import binascii

sock = socket(AF_INET, SOCK_RAW, getprotobyname('icmp'))
ICMP_ECHO_REQUEST = 8
MAX_HOPS = 30
timeout = 1.0
ttl = 1


def checksum(string):
    csum = 0
    countTo = (len(string) // 2) * 2
    count = 0

    while count < countTo:
        thisVal = string[count + 1] * 256 + string[count]
        csum = csum + thisVal
        csum = csum & 0xffffffff
        count = count + 2

    if countTo < len(string):
        csum = csum + string[len(string) - 1]
        csum = csum & 0xffffffff

    csum = (csum >> 16) + (csum & 0xffff)
    csum = csum + (csum >> 16)
    answer = ~csum
    answer = answer & 0xffff
    answer = answer >> 8 | (answer << 8 & 0xff00)

    if sys.platform == 'darwin':
        answer = htons(answer) & 0xffff
    else:
        answer = htons(answer)

    return answer

def receive(destaddress, ID, sendTime, timeout):
    receive = select.select([sock], [], [], timeout)

    if receive == ([], [], []):
        print('*    ', end='')
        return -1

    receivePack, address = sock.recvfrom(1024)
    receiveTime = time.time()
    print('%dms   ' % ((receiveTime - sendTime) * 1000), end='')
    receivePack = receivePack[20:36]
    headerType, code, receiveChecksum, receiveID, receiveSequence, data = struct.unpack('bbHHhd', receivePack)

    if headerType == 0:
        return [0, address[0]]

    return address[0]


def send(destaddress, ID):
    sendTime = [0,0,0]
    nowTime = time.time()
    pack = struct.pack('bbHHhd', ICMP_ECHO_REQUEST, 0, 0, ID, 1, nowTime)
    theChecksum = checksum(pack)
    pack = struct.pack('bbHHhd', ICMP_ECHO_REQUEST, 0, theChecksum, ID, 1, nowTime)

    for k in range(3):
        try:
            sock.sendto(pack, (destaddress, 80))
        except error as e:
            print('Sending error. (%s)' % e)
            exit(-1)

        sendTime[k] = time.time()

    return sendTime


def traceroute(destaddress, TTL, timeout):
    try:
        dest = gethostbyname(destaddress)
        # print("traceroute " + dest + " using Python:")
    except error as e:
        print('Useless hostname! ')
        exit(-1)

    myID = os.getpid() & 0xFFFF
    sock.setsockopt(SOL_IP, IP_TTL, TTL)
    sendTime = send(dest, myID)
    print('%d    ' % ttl, end='')  # sequence
    result = None

    for j in range(3):
        result = receive(dest, myID, sendTime[j], timeout)

    if result == -1:
        print("Request Timeout")
    elif isinstance(result, list):
        if result[0] == 0:
            print(result[1])
            print('end')
            exit(0)
    else:
        print(result)




for i in range(MAX_HOPS):
    traceroute('www.baidu.com', ttl, timeout)
    ttl = ttl + 1



