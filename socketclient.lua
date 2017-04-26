local host, port = "202.58.86.29", 100
local socket = require("socket")
local tcp = assert(socket.tcp())

tcp:connect(host, port);

tcp:send("CONNECTED !!\n");

while true do
	local s, status, partial = tcp:receive()
	print(s or partial)
	if status == "closed" then break end
end
tcp:close()