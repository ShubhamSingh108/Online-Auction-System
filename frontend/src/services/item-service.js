import { myAxios } from "./helper";

export const loadAllCategories=()=>{
    return myAxios.get('/api/items/').then(Response=>{ return Response.data})
}

