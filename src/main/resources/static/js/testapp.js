var test = {};
var testResult = {};
var currentQuestion = -1;
var answersGiven = [];


window.onload = function(){
    displayNewTestForm();
}

function endButtonHandler(){
    saveAnswer();
    testResult.endTime = new Date();
    clearTestForm();
    testResult.result = evaluateTest();
    testResult.maxPoint = test.maxPoint;
    displayResult();
    sendResult();
    displayNewTestForm();
}

function nextButtonHandler(){
    saveAnswer();
    clearTestForm();
    displayQuestion();
}

function startTestButtonHandler(){
    testResult.user = {};
    testResult.user.userName = getUserName();
    getFullTest();
}

function getFullTest() {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'api/test');
    xhr.onreadystatechange = function () {
      var DONE = 4;
      var OK = 200;
      if (xhr.readyState === DONE) {
        if (xhr.status === OK) {
          test = JSON.parse(xhr.responseText);
          testResult.startTime = new Date();
          currentQuestion = -1;
          clearTestForm();
          displayQuestion();
          console.log(test);
        } else {
          console.log('Error: ' + xhr.status);
        }
      }
    };
    xhr.send(null);
}

function sendResult() {
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'api/result');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.responseType = 'json';
    xhr.onreadystatechange = function () {
      var DONE = 4;
      var OK = 200;
      if (xhr.readyState === DONE) {
        if (xhr.status === OK) {
            init();
        } else {
          console.log('Error: ' + xhr.status);
        }
      }
    };
    xhr.send(JSON.stringify(testResult));
    return false;
}

function init(){
    testResult = {};
    answersGiven = [];
}

function getUserName(){
    var nodeUserName = document.getElementById("txtUserName");
    if(nodeUserName.value != ''){
        return nodeUserName.value;
    }
    return "Anonymous";
}

function displayQuestion(){
    var question = getNextQuestion();
    if(question){
        var nodeTestForm = document.getElementById('testForm');
        var nodeQuestion = document.createElement('p');
        nodeQuestion.innerHTML = question.question;
        nodeTestForm.appendChild(nodeQuestion);
        nodeTestForm.appendChild(document.createElement('br'));
        for(var i = 0; i < question.answers.length; i++){
            var radioButton = document.createElement('input');
            radioButton.setAttribute('type', 'radio');
            radioButton.setAttribute('name', 'rbnAnswer');
            radioButton.setAttribute('id', 'rbnAnswer' + i);
            radioButton.setAttribute('value', i);
            nodeTestForm.appendChild(radioButton);
            var nodeLabel = document.createElement('label');
            nodeLabel.setAttribute('for', 'rbnAnswer' + i);
            nodeLabel.innerHTML = question.answers[i].text;
            nodeTestForm.appendChild(nodeLabel);
            nodeTestForm.appendChild(document.createElement('br'));
        }
        var buttonEndTest = document.createElement('button');
        buttonEndTest.setAttribute('type', 'button');
        buttonEndTest.innerHTML = 'End test';
        buttonEndTest.onclick = endButtonHandler;
        nodeTestForm.appendChild(buttonEndTest);
        if(!lastQuestion()){
            var buttonNext = document.createElement('button');
            buttonNext.setAttribute('type', 'button');
            buttonNext.innerHTML = 'Next';
            buttonNext.onclick = nextButtonHandler;
            nodeTestForm.appendChild(buttonNext);
        }
    }
}

function getNextQuestion(){
    currentQuestion++;
    console.log(test.questions);
    if(currentQuestion < test.questions.length){
        return test.questions[currentQuestion];
    }
    return null;
}

function lastQuestion(){
    return test.questions.length == currentQuestion + 1;
}

function saveAnswer(){
    var answerIndex = -1;
    var radioButtonsArray = document.getElementsByName('rbnAnswer');
    for(var i = 0; i < radioButtonsArray.length; i++){
        if(radioButtonsArray[i].checked){
            answerIndex = i;
        }
    }
    if(answerIndex > -1){
        answer(answerIndex);
    }
}

function answer(number){
    var answer = {};
    answer.questionNumber = currentQuestion;
    answer.answerNumber = number;
    answersGiven.push(answer);
}

function clearTestForm(){
    var nodeTestForm = document.getElementById("testForm");
    nodeTestForm.innerHTML = "";
}

function evaluateTest(){
    var result = 0;
    for(var i = 0; i < answersGiven.length; i++){
        var answer = answersGiven[i];
        result += test.questions[answer.questionNumber].answers[answer.answerNumber].point;
    }
    return result;
}

function displayResult(){
    var nodeTestForm = document.getElementById('testForm');
    var nodeResultText = document.createElement('p');
    nodeResultText.innerText = 'Your result is ' + testResult.result + '/' + testResult.maxPoint;
    nodeTestForm.appendChild(nodeResultText);
}

function displayNewTestForm(){
    var nodeTestForm = document.getElementById('testForm');
    var nodeLabel = document.createElement('label');
    nodeLabel.setAttribute('for', 'txtUserName');
    nodeLabel.innerText = 'User name: ';
    nodeTestForm.appendChild(nodeLabel);
    var nodeUserName = document.createElement('input');
    nodeUserName.setAttribute('type', 'text');
    nodeUserName.id = 'txtUserName';
    nodeTestForm.appendChild(nodeUserName);
    var nodeStartButton = document.createElement('button');
    nodeStartButton.setAttribute('type', 'button');
    nodeStartButton.innerHTML = 'Start test';
    nodeStartButton.onclick = startTestButtonHandler;
    nodeTestForm.appendChild(nodeStartButton);
}