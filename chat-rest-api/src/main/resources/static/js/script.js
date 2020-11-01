let user
if(document.cookie.split('user=')[1]){
	user = document.cookie.split('user=')[1].split(';')[0]
}else {
	user = Math.random()
	document.cookie = "user=" + user
	console.log(document.cookie)
}


get_data()
document.querySelector(".Chat_Button").addEventListener("click", function(event){
	send_message()
})
document.querySelector(".Chat_Input").addEventListener("keydown", function(event){
	if(event.keyCode == 13){
		send_message()
		event.preventDefault()
	}
})


async function get_data(){
	var result = ""
	var data = await request("http://super-pizdatiy-chat.herokuapp.com/get")
	data.forEach(element => {
		if(element.user == user){
			result += create__message(element.text, element.user, element.time, "right")
		}else{
			result += create__message(element.text, element.user, element.time, "left")
		}
	});
	document.querySelector('.Chat_MessageZone').innerHTML = result
//	scrollTop()
	setTimeout(get_data, 1000)
}

async function send_message() {
	var text = document.querySelector(".Chat_Input").value
	if(text != ""){
		var date = new Date()
		var hours = date.getHours()
		var minutes = date.getMinutes()
		document.querySelector(".Chat_Input").value = ""
		if(hours<10){
			hours = "0"+hours
		}
		if(minutes<10){
			minutes = "0"+minutes
		}
		var time = hours + ":" + minutes
		document.querySelector('.Chat_MessageZone').innerHTML += create__message(text, user, time, "right")
		var responce = await request(`http://super-pizdatiy-chat.herokuapp.com/put?user=${user}&time=${time}&text=${text}`)
		if(!responce =="Success"){
			console.log(responce)
		}
		scrollTop()
	}
}

function scrollTop(){
	var MessageZone = document.querySelector('.Chat_MessageZone')
	MessageZone.scrollTop = MessageZone.scrollHeight
}


function create__message(text, user, time, position){
	return `<div class="widthBlock">
                <div class="message ${position}">
                    <div class="text">${text}</div>
                    <div class="time">${time}</div>
            	</div>
            </div>`
}





async function request(url, method = 'GET', data=null)
{
	try {
		const headers = {}
		let body

		if(data){
			headers['Content-Type'] = 'application/json'
			body = JSON.stringify(data)
		}

		const response = await fetch(url, {
			method,
			headers,
			body
		})
		return await response.json()
	}
	catch(e) {
		console.warn('Error: ', e.message)
	}
}