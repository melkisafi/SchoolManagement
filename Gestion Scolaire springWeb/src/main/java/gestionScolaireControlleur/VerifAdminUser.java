package gestionScolaireControlleur;

import javax.servlet.http.HttpSession;

public class VerifAdminUser {
	public static boolean isAdmin(HttpSession s) {
		if (s.getAttribute("role") != null) {
			if (s != null) {
				return s.getAttribute("role").equals("ADMIN") ? true : false;
			} else
				return false;
		} else {
			return false;
		}
	}

	public static boolean isAutorized(HttpSession s, Long id) {
		if (s.getAttribute("role") != null) {
			if (s != null) {
				if (s.getAttribute("role").equals("USER") && s.getAttribute("idEtab") == id) {
					return true;
				} else
					return false;
			} else
				return false;	
		}else{
			return false;
		}

	}
	
	public static boolean isConnected(HttpSession s) {
		if (s.getAttribute("role") != null) {
			return true;
		}else{
			return false;
		}
	}
}
