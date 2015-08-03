$('#showMessage').on('show.bs.modal', function (event) {
    $(this).find('.modal-body input#sender').val($(event.relatedTarget).data('sender'));
    $(this).find('.modal-body input#date').val($(event.relatedTarget).data('date'));
    $(this).find('.modal-body input#subject').val($(event.relatedTarget).data('subject'));
    $(this).find('.modal-body textarea#text').val( $(event.relatedTarget).data('text'));
});

$('#sendMessage').on('show.bs.modal', function (event) {
    $(this).find('.modal-body input#userid').val($(event.relatedTarget).data('userid'));
    $(this).find('.modal-body input#contactid').val($(event.relatedTarget).data('contactid'));
});