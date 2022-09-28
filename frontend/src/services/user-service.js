import { myAxios } from "./helper";

export const signUp=(user)=>{

    return myAxios
    .post('/api/auth/register',user)
    .then((response)=> response.data)

};

//In loginDetail--> username+password
export const loginUser=(loginDetail)=>{
    return myAxios.post('/api/auth/login',loginDetail).then((response)=>response.data);
}