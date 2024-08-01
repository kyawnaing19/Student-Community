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
