$(function () {
    $('#addressForm').livequery('.phone-number-tmpl', function (element) {
        $(element).find('input[name="phoneNumber[]"]').mask('+00(000) 000-00-00', {
            placeholder: '+__(___) ___-__-__',
            clearIfNotMatch: true
        });
    });
});