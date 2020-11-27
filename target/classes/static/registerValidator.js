function validate() {
    var login = document.forms["register-form"]["login"].value;
    var pass = document.getElementById("pass").value;
    var pass2 = document.getElementById("pass2").value;

    var regex = new RegExp("[A-Za-z0-9]{5}.*");
    var flag = true;

    if(!regex.test(login)) {
        flag = false;
    }

    if(!regex.test(pass)) {
        flag = false;
    }

    if(!regex.test(pass2)) {
        flag = false;
    }

    if(pass != pass2) {
        flag = false;
    }

    return flag;
}