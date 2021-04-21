package br.com.alura.forum.controller.dto;

public enum AuthTypes
{
    Bearer("Bearer");

    private AuthTypes type;

    AuthTypes(String type)
    {
        this.type = get(type);
    }

    public AuthTypes getType()
    {
        return type;
    }

    public void setType(AuthTypes type)
    {
        this.type = type;
    }

    public AuthTypes get(String type)
    {
        switch (type)
        {
            case "Bearer":
                return Bearer;

            default:
                return Bearer;
        }
    }
}
