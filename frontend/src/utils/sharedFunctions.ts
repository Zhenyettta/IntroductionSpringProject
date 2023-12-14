import {AUTHORIZATION_KEY, EXPIRES_AT_KEY, ROLE_KEY, USERNAME_KEY} from "./constants.ts";
import jwtDecode from "jwt-decode";
import {Indexable} from "./types.ts";

export function handleLogin(jwtToken: string) {
    const parsedJWT = jwtDecode(jwtToken) as Indexable;

    localStorage.setItem(USERNAME_KEY, parsedJWT["sub" as keyof Indexable]);
    localStorage.setItem(ROLE_KEY, parsedJWT["role" as keyof Indexable]);
    localStorage.setItem(EXPIRES_AT_KEY, parsedJWT["exp" as keyof Indexable]);
    localStorage.setItem(AUTHORIZATION_KEY, `Bearer ${jwtToken}`);

    window.location.replace("/");
}

export function handleLogout() {
    localStorage.removeItem(ROLE_KEY);
    localStorage.removeItem(USERNAME_KEY);
    localStorage.removeItem(AUTHORIZATION_KEY);
    localStorage.removeItem(EXPIRES_AT_KEY);

    window.location.replace("/");
}