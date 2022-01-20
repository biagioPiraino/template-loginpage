package piraino.template.loginpage;

public class LoginPageModel {

    public LoginPageModel(){}

    public int CalculateScaledImageHeight(int[] heightParameters)
    {
        return (heightParameters[0] - heightParameters[1]);
    }
}
