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
var inputValue;


$(document).ready(function() {
    var isCallable = true;

    function showOtpVerification() {
        if (isCallable) {
            inputValue = $('#edu-mail-placeholder').val();
            console.log(inputValue);

            var suffix = '@ucstt.edu.mm';

            if (inputValue.endsWith(suffix)) {
                $.get('/sendOtp', { email: inputValue }, function(response) {
                    // Handle success
                    document.getElementById('email-authentication').classList.add('hidden');
                    document.getElementById('otp-verification').classList.remove('hidden');
                    alert('Request successful: ' + response);
                }).fail(function(xhr, status, error) {
                    // Handle error
                    alert('Request failed: ' + error);
                });
            } else {
                alert('The email does not end with ' + suffix);
            }
            isCallable = false;
            setTimeout(function() {
                isCallable = true;
            }, 10000); // 10 seconds
        } else {
            alert('Please wait 10 seconds before calling this function again.');
        }
    }

    $('#showOtpVerification').click(function() {

        showOtpVerification();
    });
});





function showProfileCompletion() {

    const otpRequest = {
        // Populate this object with the data you need to send in the request
        otp: $('#otp-placeholder').val(),
        email: inputValue
    };

    $.ajax({
        url: '/verifyOtp',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(otpRequest),
        success: function(response) {
            document.getElementById('otp-verification').classList.add('hidden');
            document.getElementById('profile-completion').classList.remove('hidden');
            alert("otp verify success");

            console.log(response);
        },
        error: function(error) {
            alert("otp verify fail")
            console.error(error);
        }
    });
}




    //
    $(document).ready(function() {
        $('#register').click(function() {
            document.getElementById('profile-completion').classList.add('hidden');
            document.getElementById('password-session').classList.remove('hidden');
            var user = {
                username: $('#name').val(),
                rno: $('#rno').val(),
                phone: $('#phone').val()
            };

            $.ajax({
                url: '/register',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(user),

            });
        });
    });
    //




