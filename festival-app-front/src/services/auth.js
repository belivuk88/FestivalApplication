import FestivalAxios from "../apis/FestivalAxios"

export const login = async function (username, password){
    const data = {
        username: username,
        password: password
    }
    try{
        const ret = await FestivalAxios.post('users/auth', data);
        window.localStorage.setItem('jwt', ret.data);
    }catch(error){
        console.log(error);
    }
    window.location.assign("/");
}
export const logout = function(){
    window.localStorage.removeItem('jwt');
    window.location.assign("/");
}