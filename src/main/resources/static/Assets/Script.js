//--------------------Start Swiper Story----------------
var swiper = new Swiper(".mySwiper", {
    slidesPerView: 6,
    spaceBetween: 10,
    pagination: {
        el: ".swiper-pagination",
        clickable: true,
    },
    navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev",
    },
    direction: 'horizontal', // Ensures horizontal sliding
});

//.......................Window Scroll....................
window.addEventListener('scroll', () => {
    document.querySelector('.profile-popup').style.display = 'none'
})

//--------------------Start Profile Popup----------------

document.querySelectorAll('#my-profile-picture').forEach(AllProfile => {
    AllProfile.addEventListener('click', () => {
        document.querySelector('.profile-popup').style.display = 'flex';
    });
});


document.querySelectorAll('.close').forEach(AllCloser => {
    AllCloser.addEventListener('click', () => {
        document.querySelector('.profile-popup').style.display = 'none';
    });
});
let img= document.getElementById('my-profile-picture1')
document.querySelector('#profile-upload').addEventListener('change', () => {
    img.src=URL.createObjectURL(document.querySelector('#profile-upload').files[0])
    document.querySelectorAll('#my-profile-picture img').forEach(AllMyProfileImg => {
        AllMyProfileImg.src = URL.createObjectURL(document.querySelector('#profile-upload').files[0])
    })

})

//end

//popup1
document.querySelectorAll('#my-profile-picture1').forEach(AllProfile => {
    AllProfile.addEventListener('click', () => {
        document.querySelector('.profile-popup1').style.display = 'flex';
    });
});

document.querySelectorAll('.close').forEach(AllCloser => {
    AllCloser.addEventListener('click', () => {
        document.querySelector('.profile-popup1').style.display = 'none';
    });
});

//end

//Bio


function editBio() {
    let bioText = prompt("Edit your bio (max 30 words):");
    if (bioText) {
        // Truncate bio to max 30 words
        let truncatedBio = bioText.trim().split(/\s+/).slice(0, 30).join(" ");

        // Display bio on the page
        document.getElementById('userBio').textContent = truncatedBio;

        // Send bio to server
        //var username;
        console.log("session is : "+username)

        $.ajax({
            url: '/updateBio/'+username+'',
            type: 'POST',
            contentType: 'application/json',
            data: truncatedBio,
            success: function(response) {
                alert('Update successful: ' + response);

            },
            error: function(error) {
                alert('Update failed: ' + error.responseText);
            }
        });
        console.log("after request")
    }
}

