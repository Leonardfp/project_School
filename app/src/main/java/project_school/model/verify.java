package project_school.model;

public class verify {
    
    public static boolean isNumeric(String srt){
        try{
            Integer.parseInt(srt);
            return (true);
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }

   public static boolean isNumeric(int valor){
        try{
            Integer.toString(valor);
            return (true);
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }
}
