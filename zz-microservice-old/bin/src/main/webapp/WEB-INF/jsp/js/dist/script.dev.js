'use strict';

var stompClient;
var username;

var connect = function connect(event) {
  username = document.querySelector('#username').value.trim();

  if (username) {
    var login = document.querySelector('#login');
    login.classList.add('hide');
    var chatPage = document.querySelector('#chat-page');
    chatPage.classList.remove('hide');
    var socket = new SockJS('/chat-example');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
  }

  event.preventDefault();
};

var onConnected = function onConnected() {
  stompClient.subscribe('/topic/public', onMessageReceived);
  stompClient.send("/app/chat.newUser", {}, JSON.stringify({
    sender: username,
    type: 'CONNECT'
  }));
  var status = document.querySelector('#status');
  status.className = 'hide';
};

var onError = function onError(error) {
  var status = document.querySelector('#status');
  status.innerHTML = 'Could not find the connection you were looking for. Move along. Or, Refresh the page!';
  status.style.color = 'red';
};

var sendMessage = function sendMessage(event) {
  var messageInput = document.querySelector('#message');
  var messageContent = messageInput.value.trim();

  if (messageContent && stompClient) {
    var chatMessage = {
      sender: username,
      content: messageInput.value,
      type: 'CHAT',
      time: moment().calendar()
    };
    stompClient.send("/app/chat.send", {}, JSON.stringify(chatMessage));
    messageInput.value = '';
  }

  event.preventDefault();
};

var onMessageReceived = function onMessageReceived(payload) {
  var message = JSON.parse(payload.body);
  var chatCard = document.createElement('div');
  chatCard.className = 'card-body';
  var flexBox = document.createElement('div');
  flexBox.className = 'd-flex justify-content-end mb-4';
  chatCard.appendChild(flexBox);
  var messageElement = document.createElement('div');
  messageElement.className = 'msg_container_send';
  flexBox.appendChild(messageElement);

  if (message.type === 'CONNECT') {
    messageElement.classList.add('event-message');
    message.content = message.sender + ' connected!';
  } else if (message.type === 'DISCONNECT') {
    messageElement.classList.add('event-message');
    message.content = message.sender + ' left!';
  } else {
    messageElement.classList.add('chat-message');
    var avatarContainer = document.createElement('div');
    avatarContainer.className = 'img_cont_msg';
    var avatarElement = document.createElement('div');
    avatarElement.className = 'circle user_img_msg';
    var avatarText = document.createTextNode(message.sender[0]);
    avatarElement.appendChild(avatarText);
    avatarElement.style['background-color'] = getAvatarColor(message.sender);
    avatarContainer.appendChild(avatarElement);
    messageElement.style['background-color'] = getAvatarColor(message.sender);
    flexBox.appendChild(avatarContainer);
    var time = document.createElement('span');
    time.className = 'msg_time_send';
    time.innerHTML = message.time;
    messageElement.appendChild(time);
  }

  messageElement.innerHTML = message.content;
  var chat = document.querySelector('#chat');
  chat.appendChild(flexBox);
  chat.scrollTop = chat.scrollHeight;
};

var hashCode = function hashCode(str) {
  var hash = 0;

  for (var i = 0; i < str.length; i++) {
    hash = str.charCodeAt(i) + ((hash << 5) - hash);
  }

  return hash;
};

var getAvatarColor = function getAvatarColor(messageSender) {
  var colours = ['#2196F3', '#32c787', '#1BC6B4', '#A1B4C4'];
  var index = Math.abs(hashCode(messageSender) % colours.length);
  return colours[index];
};

var loginForm = document.querySelector('#login-form');
loginForm.addEventListener('submit', connect, true);
var messageControls = document.querySelector('#message-controls');
messageControls.addEventListener('submit', sendMessage, true);