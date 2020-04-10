import axios from 'axios';

// const createReactClass = require('create-react-class');

// const APIcalls = createReactClass({
//   statics: {
//     getCart: function() {
//         axios.get("http://localhost:8081/Loghmeh_war_exploded/get_user_cart/" + 1)
//         .then(res => {
//             const data = res.data;
//             console.log("getCart is called", data);
//             return data;
//         }).catch(error => {console.log(error);})},
    
//     addToCart: function(restaurantId, foodName, isFoodParty){
//         console.log("order moreeeeeeeeee");
//         event.preventDefault();
//         axios.put('http://localhost:8081/Loghmeh_exploded/put_cart', null,
//             {params: {'userId': 1, 'restaurantId': restaurantId, 'foodName' : foodName, 'isFoodParty' : isFoodParty}}
//         ).then( (response) => {return getCart();})
//         .catch((error) => {
//             if (error.response.status === 403) {
//             //   return <Example test={<p>شما مجاز به سفارش غذا از رستوران‌های متفاوت نیستید.</p>}/>
    
//             // } else {
//                 console.log(error);
//             }
//           })    
//     },
//     removeFromCart: function(restaurantId, foodName, isFoodParty){
//         console.log("order lessssssss");
//         // event.preventDefault();
//         axios.delete('http://localhost:8081/08_React_war_exploded/del_cart', 
//             {params: {'userId': 1, 'restaurantId': restaurantId, 'foodName' : foodName, 'isFoodParty' : isFoodParty}}
//         ).then( (response) => {return getCart();})
//         .catch((error) => {
//             // if (error.response.status === 403) {
//             //   return <Example test={<p>شما مجاز به سفارش غذا از رستوران‌های متفاوت نیستید.</p>}/>
    
//             // } else {
//                 console.log(error);
//             // }
//           })    
//     },
//     finalizeCart: function() {
//         console.log("finaliiiiiize");
//         event.preventDefault();
//         axios.put('http://localhost:8081/Loghmeh_exploded/finalize', null,
//             {params: {'userId': 1}}
//         ).then( (response) => {return getCart();})
//         .catch((error) => {
//             // if (error.response.status === 403) {
//             //   return <Example test={<p>شما مجاز به سفارش غذا از رستوران‌های متفاوت نیستید.</p>}/>
    
//             // } else {
//                 console.log("eroooooooor", error);
//             // }
//           })   
//     },


//   },
//   render() {
//   },
// });

// export default APIcalls;


export const getCart = () => {
    console.log("get carttttt");

    axios.get("http://localhost:8081/Loghmeh_war_exploded/get_user_cart/" + 1)
    .then(res => {
        const data = res.data;
        console.log("getCart is called", data);
        return data;
    }).catch(error => {console.log(error);})
}

export const addToCart = (restaurantId, foodName, isFoodParty) => {
    console.log("order moreeeeeeeeee");
    event.preventDefault();
    axios.put('http://localhost:8081/Loghmeh_exploded/put_cart', null,
        {params: {'userId': 1, 'restaurantId': restaurantId, 'foodName' : foodName, 'isFoodParty' : isFoodParty}}
    ).then( (response) => {return getCart();})
    .catch((error) => {
        if (error.response.status === 403) {
        //   return <Example test={<p>شما مجاز به سفارش غذا از رستوران‌های متفاوت نیستید.</p>}/>

        // } else {
            console.log(error);
        }
      })    
}

export const removeFromCart = (restaurantId, foodName, isFoodParty) => {
    console.log("order lessssssss");
    // event.preventDefault();
    axios.delete('http://localhost:8081/08_React_war_exploded/del_cart', 
        {params: {'userId': 1, 'restaurantId': restaurantId, 'foodName' : foodName, 'isFoodParty' : isFoodParty}}
    ).then( (response) => {return getCart();})
    .catch((error) => {
        // if (error.response.status === 403) {
        //   return <Example test={<p>شما مجاز به سفارش غذا از رستوران‌های متفاوت نیستید.</p>}/>

        // } else {
            console.log(error);
        // }
      })    
}

export const finalizeCart = () => {
    console.log("finaliiiiiize");
    event.preventDefault();
    axios.put('http://localhost:8081/Loghmeh_exploded/finalize', null,
        {params: {'userId': 1}}
    ).then( (response) => {return getCart();})
    .catch((error) => {
        // if (error.response.status === 403) {
        //   return <Example test={<p>شما مجاز به سفارش غذا از رستوران‌های متفاوت نیستید.</p>}/>

        // } else {
            console.log("eroooooooor", error);
        // }
      })   
}