local socket = require("socket")
local server = assert(socket.bind("*", 0))
local ip, port = server:getsockname()

-- print a message informing what's up
print("Please telnet to localhost on port " .. port)
print("After connecting, you have 10s to enter a line to be echoed")

while 1 do

  -- wait for a connection from any client
  local client = server:accept()

  -- make sure we don't block waiting for this client's line
  client:settimeout(10)

  -- receive the line
  local line, err = client:receive()

  -- if there was no error, send it back to the client
  if not err then client:send(line .. "\n") end

  -- done with client, close the object
  client:close()
end
