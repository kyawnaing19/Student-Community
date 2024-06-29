var btn=document.getElementById('btn')


function showEduMail() {
    document.getElementById('edu-mail-fields').classList.remove('hidden');
    document.getElementById('gmail-fields').classList.add('hidden');
    document.getElementById('edu-mail-placeholder').placeholder = "Enter Your Edu-mail";
      btn.style.left='0'
   
}
function showGMail() {
    document.getElementById('edu-mail-fields').classList.add('hidden');
    document.getElementById('gmail-fields').classList.remove('hidden');
    document.getElementById('edu-mail-placeholder').placeholder = "Enter Your G-mail";
    btn.style.left='210px'
 
}

function showOtpVerification() {
    document.getElementById('email-authentication').classList.add('hidden');
    document.getElementById('otp-verification').classList.remove('hidden');

    var inputValue = $('#edu-mail-placeholder').val();
    console.log(inputValue);

    $.get('/sendOtp', { email: inputValue }, function(response) {
        // Handle success
        alert('Request successful: ' + response);
    }).fail(function(xhr, status, error) {
        // Handle error
        alert('Request failed: ' + error);
    });
}


function showProfileCompletion() {
    document.getElementById('otp-verification').classList.add('hidden');
    document.getElementById('profile-completion').classList.remove('hidden');
}
function showPassword() {
    document.getElementById('profile-completion').classList.add('hidden');
    document.getElementById('password-session').classList.remove('hidden');
}


