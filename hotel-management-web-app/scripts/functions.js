
function getEvents(callback) {
	axios.get("http://localhost:8080/api/events")
		.then(res => {
			const eventList = res.data; 
			callback(eventList);
		})
}

function printEventsAsTable(list) {
	var text="<table><tr><th>Name</th><th>Description</th><th>Time</th><th>Location</th></tr>";
	for (let i = 0; i < list.length; i++) {
		text += "<tr><td>"+list[i].name+"</td><td>"+list[i].description+"</td><td>"+list[i].eventDateTime+"</td><td>"+list[i].location+"</td></tr>";
	}
	text+="</table>";
	document.getElementById("eventTable").innerHTML = text;
}


function getRooms() {

	axios.get("http://localhost:8080/api/rooms/Get-All-Available-Rooms")
		.then(res => {
			const roomsList = res.data;
			printRooms(roomsList);
		})
}

function printRooms(list) {
	var selectContent;
	for (let i = 0; i < list.length; i++) {
		selectContent += "<option value='" + list[i].id + "'>" + list[i].type + "</option>";
	}
	document.getElementById("room-type").innerHTML = selectContent;
}

function createReservation() {
	var name = document.getElementById("name1").value + " "+ document.getElementById("surname").value;
	var contactNumber = document.getElementById("contactNumber").value;
	var email = document.getElementById("email").value;
	var address = document.getElementById("address").value;
	var arrivaldate = document.getElementById("arrival-date").value;
	var leavedate = document.getElementById("leave-date").value;

	var roomid = document.getElementById("room-type").value;

	var guestid="";


	if(restrictions()==false) {
		return false;
	}
		const guest = {
			"name": name,
			"contactNumber": contactNumber,
			"email": email,
			"address": address
		}
		axios.post("http://localhost:8080/api/guests", guest)
		.then(res => {
			const guest = res.data;			
			guestid=res.data.id;
			const reservation = {
				"guestId": guestid,
				"roomId": roomid,
				"startDate": arrivaldate,
				"endDate": leavedate,
				"status": "PENDING"
			}


			axios.post("http://localhost:8080/api/reservations", reservation)
			.then(res => {
				const reservation = res.data;
				document.getElementById("message2").innerHTML = "<b style='color:blue'>Reservation created successfully!!</b>";
				console.log("Reservation created successfully!!"+reservation.id);
				
				
			}).catch(function(error) {
				document.getElementById("message2").innerHTML = "<b style='color:red'>An error occured!"+error+"</b>";
				
			});	
	
			document.getElementById("message").innerHTML = "<b style='color:blue'>Guest created successfully!!</b>";
			console.log("Guest created successfully!!"+guestid);


		}).catch(function(error) {
			document.getElementById("message").innerHTML = "<b style='color:red'>An error occured!"+error+"</b>";
			
		});

	return false;
}

function restrictions() {
	var name = document.getElementById("name1").value + " "+ document.getElementById("surname").value;
	var number = document.getElementById("contactNumber").value;
	var email = document.getElementById("email").value;
	var address = document.getElementById("address").value;

	var arrivaldate = document.getElementById("arrival-date").value;
	var leavedate = document.getElementById("leave-date").value;

  if(name.length>35 ||name == null ) {
    alert("Name is not acceptable");
    return false;
  }
  if(number.length>12 || number == null || number == "") {
    alert("Phone number is not acceptable");
    return false;
  }
	if (email == null || email == "") {
		alert("Please enter guest email");
		return false;
	}
	if (address == null || address == "") {
		alert("Please enter guest address");
		return false;
	}
	if (arrivaldate == null || arrivaldate == "") {
		alert("Please enter guest arrival date");
		return false;
	}
	if (leavedate == null || leavedate == "") {
		alert("Please enter guest leave date");
		return false;
	}
	
}
function restrictionsCancelReservation() {
	var id=document.getElementById("guestid2").value; // cancel reservation
	if(id == null || id == "") {
		alert("Guest id is not acceptable");
		return false;
	  }
}
function restrictionsGetReservation() {
	var id=document.getElementById("guestid").value; // get reservation
  
  if(id == null || id == "") {
    alert("Guest id is not acceptable");
    return false;
  }
	
}

function getReservation() {
var id=document.getElementById("guestid").value;

if(restrictionsGetReservation()==false) { // düzenle
	return false;
}

	axios.get(`http://localhost:8080/api/reservations/Guest/${id}`)
		.then(res => {
			const reservationDetails = res.data;			
			printReservationAsTable(reservationDetails);
		}).catch(function(error) {
			console.log(error);
		});

		return false;
}

function cancelReservation() {
	var id=document.getElementById("guestid2").value;

	if(restrictionsCancelReservation()==false) { // düzenle
		return false;
	}
	axios.get(`http://localhost:8080/api/reservations/Guest/${id}`) // get guest reservation
		.then(res => {
			const resid = res.data[0].id;	
		
			
			console.log("Response:", res); 
			axios.delete(`http://localhost:8080/api/reservations/${resid}`) // delete by reservation id
			.then(response => {
				alert('Reservation deleted successfully', response.data);
			})
			.catch(function(error) {
				console.log(error);
			});
		
		
		}).catch(function(error) {
			console.log(error);
		});

	return false;
}




function printReservationAsTable(list) {
	var text="<table><tr><th>Guest ID</th><th>Room ID</th><th>Start Date</th><th>End Date</th></tr>";
	for (let i = 0; i < list.length; i++) {
		text += "<tr><td>"+list[i].guestId+"</td><td>"+list[i].roomId+"</td><td>"+list[i].startDate+"</td><td>"+list[i].endDate+"</td></tr>";
	}
	text+="</table>";
	document.getElementById("reservationTable").innerHTML = text;
}
