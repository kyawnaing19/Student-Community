function addLike(userId,postId){
    $.get('/likes/addLike', { userId: userId, postId:postId }, function(response) {

         $('#LikeIcon-'+postId+'').attr('class','fa-brands fa-gratipay');
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
            $('#Like-'+postId+'').attr('onclick','addLike('+userId+','+postId+')');
        },
        error: function(xhr, status, error) {
            alert('Request failed: ' + error);
        }
    });
}
