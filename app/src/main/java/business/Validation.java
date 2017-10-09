package business;

/**
 * Created by User on 9/30/2015.
 */
public class Validation {

    public void validateDonor(DonorTO donorTO) throws Exception{
        if(!isValidEmail(donorTO.getEmail())){
            throw new Exception("Invalid Email");
        }
        if(!isValidPhoneNo(donorTO.getPhone())){
            throw new Exception("Invalid Phone Number");
        }
        if(!isValidAge(donorTO.getAge())){
            throw new Exception("Age should be above 16");
        }
    }

    public Boolean isValidDonorName(String name){
        Boolean isValid = true;
        //code to validate name
        return isValid;
    }

    public Boolean isValidEmail(String email){
        int length = email.length();
        if(email.charAt(0) == '@' || email.charAt(0) == '.' || email.charAt(length-1)=='@' || email.charAt(length-1)== '.')return false;
        int indexOfAt = email.indexOf('@');
        if(indexOfAt == -1)return false;
        else if(email.charAt(indexOfAt+1) == '.')return false;
        for(int i = indexOfAt+2; i < length-1; i++){
            if(email.charAt(i) == '.')return true;
        }
        return false;
    }

    public Boolean isValidPhoneNo(String phoneNo){
        for(int i = 0; i < phoneNo.length(); i++){
            if(!(Character.isDigit(phoneNo.charAt(i)))){
                return false;
            }
        }
        return true;
    }

    public Boolean isValidAge(Integer age){
        if(age<16)return false;
        return true;
    }
}
