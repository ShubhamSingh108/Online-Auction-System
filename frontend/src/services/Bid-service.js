import { myAxios } from "./helper";

const loadAllEvents=()=>{
    return myAxios.get(``).then(response=>{return response.data})
}

//create item function
export const CreateEventPost=(eventitemData)=>{
    console.log(eventitemData)
 return myAxios.post(`/api/user/${eventitemData.userId}/event/${eventitemData}/enrolment`,eventitemData).then(response=> response.data)
}
//post(`/api/user/${postItemData.userId}/items`,postItemData)