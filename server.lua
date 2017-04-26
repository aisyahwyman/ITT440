local socket = require("socket")
host = host or "202.58.86.29"
port = port or 8080
if arg then
	host = arg[1] or host
	port = arg[2] or port
end
print("Binding to host '" ..host.. "' and port " ..port.. "...")
local server = assert(socket.bind("*", 0))
local ip, ports = server:getsockname()
assert(ip, ports)
print("Waiting connection from talker on " .. i .. ":" .. p .. "...")
c = assert(server:accept())
print("Connected. Here is the stuff:")
l, err = c:receive()
while not err do
	reversedString = l.reverse(l)
	c:send(reversedString .. "\n")
	l, err = c:receive()
end
print(err)
