<template>

  <div class="container">

    <div v-for="item in newPriceList">
      <div class="card border-primary mb-3" style="">
        <div class="card-header">Name: {{item.productName}}</div>
        <div class="card-body">
          <h4 class="card-title">Unit price: {{item.unitPrice}}</h4>
        </div>
      </div>
      <table class="table table-hover">
        <thead>
        <tr>
          <th scope="col">Quantity </th>
          <th scope="col">Price</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="details in item.productDetailsList">
          <td>{{details.productQty}}</td>
          <td>{{details.price}}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>

</template>


<script>
  import axios from 'axios';
 export default {

   data(){
     return{
       newPriceList:[]
     }
   },

   methods:{

     priceList(){

       axios.get("http://localhost:8595/price/")
         .then(response => {
           this.newPriceList = response.data;

         })
         .catch(e => {
           this.errors.push(e)
         })
     }
   },
   created: function(){
     this.priceList();
   }
 }

</script>


<style>

</style>
