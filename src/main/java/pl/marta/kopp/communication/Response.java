package pl.marta.kopp.communication;

public class Response {
    private Boolean isSuccess;
    private String message;


    public Response(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public static Response aFailureResponse(String message) {
        Response response = new Response(false);
        response.setMessage(message);
        return response;
    }

    public static Response aSuccessfulResponse(){
    return new Response(true);
    }


    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
