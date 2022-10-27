$(function() {
   $('#signup>form').validate({
      rules: {
         name: { required: true },
         email: { required: true, email: true },
         website: { url: true },
         password: { minlength: 6, maxlength: 12 },
         passconf: { equalTo: '#password' }
      },
      success: function(label) {
         label.addClass('valid');
         label.text('success!')
      }
   });

   //----attr -> prop()
   $('.check-all').click(function() {
      $('.agree').prop('checked', $('.check-all').is(":checked"));
   })
})