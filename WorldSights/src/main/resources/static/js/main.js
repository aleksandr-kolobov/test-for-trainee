$(function(){

    $('.add-town-button').click(function(){
        $('.add-town-form').css('display', 'flex')
    })

    $('.add-town-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none')
        }
    })

    $('.save-add-town-button').click(function() {
        var data = $('.add-town-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/towns/',
            data: data,
            success: function(response) {
                $('.add-town-form').css('display', 'none');
            }
        })
        return false;
    })

    $('.correct-name-town-button').click(function(){
        $('.correct-name-town-form').css('display', 'flex')
    })

    $('.correct-name-town-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none')
        }
    })

    $('.save-correct-name-town-button').click(function() {
        var data = $('.correct-name-town-form form').serialize();
        $.ajax({
            method: "PATCH",
            url: '/towns/',
            data: data,
            success: function(response) {
                $('.correct-name-town-form').css('display', 'none');
            }
        })
        return false;
    })

    $('.correct-id-town-button').click(function(){
        $('.correct-id-town-form').css('display', 'flex')
    })

    $('.correct-id-town-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none')
        }
    })

    $('.save-correct-id-town-button').click(function() {
        var data = $('.correct-id-town-form form').serialize();
        var dataArray = $('.correct-id-town-form form').serializeArray();
        var id =dataArray[0]['value'];
        $.ajax({
            method: "PATCH",
            url: '/towns/' + id,
            data: data,
            success: function(response) {
                $('.correct-id-town-form').css('display', 'none');
            }
        })
        return false;
    })

    $('.add-sight-button').click(function(){
        $('.add-sight-form').css('display', 'flex')
    })

    $('.add-sight-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none')
        }
    })

    $('.save-add-sight-button').click(function() {
        var data = $('.add-sight-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/sights/',
            data: data,
            success: function(response) {
                $('.add-sight-form').css('display', 'none');
            }
        })
        return false;
    })

    $('.correct-sight-button').click(function(){
        $('.correct-sight-form').css('display', 'flex')
    })

    $('.correct-sight-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none')
        }
    })

    $('.save-correct-sight-button').click(function() {
        var data = $('.correct-sight-form form').serialize();
        var dataArray = $('.correct-sight-form form').serializeArray();
        var id =dataArray[0]['value'];
        $.ajax({
            method: "PATCH",
            url: '/sights/' + id,
            data: data,
            success: function(response) {
                $('.correct-sight-form').css('display', 'none');
            }
        })
        return false;
    })

    $('.delete-sight-button').click(function(){
        $('.delete-sight-form').css('display', 'flex')
    })

    $('.delete-sight-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none')
        }
    })

    $('.save-delete-sight-button').click(function() {
        var data = $('.delete-sight-form form').serialize();
        var dataArray = $('.delete-sight-form form').serializeArray();
        var id =dataArray[0]['value'];
        $.ajax({
            method: "DELETE",
            url: '/sights/' + id,
            data: data,
            success: function(response) {
                $('.delete-sight-form').css('display', 'none');
            }
        })
        return false;
    })







})