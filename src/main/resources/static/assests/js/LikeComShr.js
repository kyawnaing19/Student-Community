var user_id = parseInt(sessionStorage.getItem("id"), 10);

function addLike(userId,postId){
    $.get('/likes/addLike', { userId: userId, postId:postId }, function(response) {

        console.log("aaaaaaaaaaa"+postId)
         $('#LikeIcon-'+postId+'').attr('class','fa-brands fa-gratipay');
        let currentValue = parseInt($('#likeCount'+postId+'').text(), 10);

// Increment the value by 1
        let newValue = currentValue + 1;

// Set the updated value back to the span
        $('#likeCount'+postId+'').text(newValue);
        $('#Like-'+postId+'').attr('onclick','deleteLike('+userId+','+postId+')');



    }).fail(function(xhr, status, error) {
        alert('Request failed: ' + error);
    });
}

function deleteLike(userId, postId) {
    $.ajax({
        url: '/likes/deleteLike', // your delete mapping URL
        type: 'DELETE',
        data: { userId: userId, postId: postId },
        success: function(response) {
            $('#LikeIcon-'+postId+'').attr('class','fa fa-heart');
            // Get the current value from the span, which is a string, and convert it to a number
            let currentValue = parseInt($('#likeCount'+postId+'').text(), 10);

// Decrement the value by 1
            let newValue = currentValue - 1;

// Set the updated value back to the span
            $('#likeCount'+postId+'').text(newValue);

            $('#Like-'+postId+'').attr('onclick','addLike('+userId+','+postId+')');
        },
        error: function(xhr, status, error) {
            alert('Request failed: ' + error);
        }
    });
}


function shModal(id){
    var modal=document.getElementById('shareModal'+id);
    var span=document.getElementById('span-'+id);
    modal.style.display='block';
    span.onclick = function() {
        modal.style.display = "none";
    };
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    };
}


function sharePost(id){
    let selectedValue='Public';
    let parentId=document.getElementById('op-'+id+'').value;
    let content=document.getElementById('shareContent-'+id+'').value;
    // Example of using this in an event handler
    $('shareAudience-'+id+'').change(function() {
         selectedValue = $(this).val();
        console.log(selectedValue);
    });

    $.ajax({
        url: '/posts/sharePost',
        type: 'GET',
        data: {
            uid: user_id,
            pid: parentId,
            aud: selectedValue,
            content: content
        },
        success: function(response) {
            alert("Shared Successfully");
            if (parseInt(sessionStorage.getItem("id"))=== user_id) {
                window.location.reload();
            }
        },
        error: function(xhr, status, error) {
            alert('Request failed: ' + error);
        }
    });


}

function timeAgo(dateString) {
    const date = new Date(dateString);
    const now = new Date();
    const diffInSeconds = Math.floor((now - date) / 1000);

    const minutes = Math.floor(diffInSeconds / 60);
    const hours = Math.floor(minutes / 60);
    const days = Math.floor(hours / 24);
    const months = Math.floor(days / 30);
    const years = Math.floor(months / 12);

    if (years >= 1) {
        return years === 1 ? "1 year ago" : `${years} years ago`;
    } else if (months >= 1) {
        return months === 1 ? "1 month ago" : `${months} months ago`;
    } else if (days >= 1) {
        return days === 1 ? "1 day ago" : `${days} days ago`;
    } else if (hours >= 1) {
        return hours === 1 ? "1 hour ago" : `${hours} hours ago`;
    } else {
        return minutes === 1 ? "1 minute ago" : `${minutes} minutes ago`;
    }
}

// Example usage:
console.log(timeAgo("2024-08-02T00:44:29.173638"));

