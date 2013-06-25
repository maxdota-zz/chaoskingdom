package springJDBC;

public class Player
{
	private int id;
    private String username;
    private String password;
	
	
	public Player(int id, String name, String pass) {
		this.id = id;
		username = name;
		password = pass;
	}

    public String getName() {
        return username;
    }

	@Override
	public String toString() {
		return "Player [id = " + id + ", username = " + username + ", password = " + password
				+ "]";
	}
	
	
}
