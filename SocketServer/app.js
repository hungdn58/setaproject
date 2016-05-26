var express = require("express");
var app = express();
var server = require("http").createServer(app);
var io = require("socket.io").listen(server);
var fs = require("fs");
server.listen(process.env.PORT || 3000);

app.get("/", function(req, res){
	res.sendFile(__dirname + "/index.html");
});

var rooms = [];
var mangUsernames = [];
var ketqua = false;

io.sockets.on('connection', function (socket){
	console.log("Co nguoi connect ne");

	socket.on('client-gui-username', function(data) {
		
		if (mangUsernames.indexOf(data) > -1) {
			console.log("DA TON TAI " + data);
			ketqua = false;
		}else {
			mangUsernames.push(data);
			console.log("ADD USERNAME THANH CONG");
			console.log(socket.id);
			socket.username = data;
			socket.un = socket.id;
			ketqua = true;

			io.sockets.emit("server-gui-username", {user: data, id: socket.id});
		}

		socket.emit("ketquaDangKyUn", {noidung : ketqua});
	});

	socket.on('client-chat-client', function(receiver){
		var roomname = receiver + socket.username;
		var invert_roomname = socket.username + receiver;
		if (rooms.indexOf(roomname) > -1) {
			socket.room = roomname;
			socket.join(roomname);
			console.log("ENTER EXISTS ROOM OK: " + roomname);
		} else if (rooms.indexOf(invert_roomname) > -1) {
			socket.room = invert_roomname;
			socket.join(invert_roomname);
			console.log("ENTER EXISTS ROOM OK: " + invert_roomname);
		} else {
			var current_room = socket.username + receiver;
			socket.room = current_room;
			socket.join(current_room);
			console.log("CREATE ROOM OK: " + current_room);
			rooms.push(current_room);
		}
		
	});

	socket.on('client-gui-chat', function (data) {
		// we tell the client to execute 'updatechat' with 2 parameters
		// var clientNumber = io.sockets.adapter.rooms[socket.room].length;
		// if (length < 2) {

		// }else{
			
		// }
		io.sockets.in(socket.room).emit('updatechat', {username: socket.username, message: data});
		console.log("CREATE MESSAGE OK: " + data);
		
	});

	socket.on('signout', function(data) {
		socket.leave(socket.room);
		socket.room = "";
		var index = mangUsernames.indexOf(data);
		mangUsernames.splice(index, 1);
		console.log("User logout: " + data);
	})

});