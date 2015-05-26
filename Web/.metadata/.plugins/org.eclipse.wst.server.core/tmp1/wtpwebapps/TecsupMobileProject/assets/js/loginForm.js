



        $(document).ready(function(){
             jQuery(function($){$("#loginForm").submit(function(e){
                        e.preventDefault();
                        clearError();
                        var form = $(this);
                        $.ajax({ 
                             url   : form.attr('action'),
                             type  : form.attr('method'),
                             data  : form.serialize(), // data to be submitted
                             success: function(response){
                                console.log(response);
                                //Handle Response
                                if (response == "OK"){
                                    clearForm();
                                    

                                }
                                else
                                {
                                    ele = $('#'+response).get(0);
                                    formError(ele,"Please provide a valid " + ele.name);
                                }



                             }
                        });
                        return false;
                    });

         });

        })
       
 



function formError(ele, message) {

    ele.placeholder = message;
    ele.classList.add("errormessage");

}

function clearError(){
    var eles = $("#contactForm").find(":input").toArray()

    eles.forEach(function(ele){
        ele.classList.remove("errormessage");

    });   
}

function clearForm(){
    $("#ajax_contact").load("/AJAX_contact_form")   
}

function submitForm(){
    $("#contactForm").submit()
}



function validateEmail(email) {
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}


function formError(ele, message) {
    ele.classList.add("error");
    ele.placeholder = message;
    document.getElementById("error_message").innerHTML = message;
}
    








