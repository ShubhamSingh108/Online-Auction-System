import { privateAxios } from "./helper"
import { myAxios } from "./helper"
//create post function
export const createItem=(postItemData)=>{
    //console.log(postItemData)
    return privateAxios.post(`/api/user/${postItemData.userId}/items`,postItemData).then(response=> response.data)
   
    ///api/items/1/image
}



//get all items
export const loadAllItems=()=>{

    return myAxios.get('/api/items/').then(response=> response.data)
}


//load single item details of givenID
export const loadItem=(itemID)=>{
    return myAxios.get("/api/items/" + itemID).then(response=> response.data)
}

//load 
export const loadItembyUserId=(itemID)=>{
    return myAxios.get(`/api/user/${itemID}/items` + itemID).then(response=> response.data)
}



//upload item image
export const uploadItemImage=(image,itemID)=>{   
 let formData = new FormData();
 formData.append("image",image);
 
 return privateAxios.post(`/api/items/${itemID}/image`,formData,{
    headers: {
        "Content-Type": "multipart/form-data",
      },
 }).then((response)=> response.data);
};



//loadItemUserWise
 export function loadItemUserWise(userId) {
    return privateAxios.get(`/api/user/${userId}/items`).then((response)=> response.data);
 }

 //delete post
export function deleteItemService(itemID) {
    return privateAxios.delete(`/api/items/${itemID}`).then((res) => res.data);
  }

//update post 
export function updateItem(item,itemID){
    console.log(item)
    return privateAxios.put(`/api/items/${itemID}`,item).then((res) => res.data);
}