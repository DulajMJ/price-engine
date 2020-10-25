<template>
  <div class="container">
    <div class="col-md-8">
      <div>
        <div class="form-group">
          <label for="exampleSelect1">Product</label>
          <select class="form-control" id="exampleSelect1"  v-model="selected">
            <option v-for="option in newProductList" v-bind:value="option.productId">{{option.productName}}</option>
          </select>
        </div>
        <div class="form-group">
          <label for="exampleSelect1">Quantity</label>
          <input type="text"  class="form-control " placeholder="0"  @input="keyPressed" v-model="items.producctQty">
        </div>
      </div>

      <div class="card border-primary mb-3" style="">
        <div class="card-header">Price: {{price}}</div>
        <div class="card-body">

          <h4 class="card-title"></h4>
        </div>
      </div>
      <button @click="addToCart(items)" class="btn btn-sm btn-primary">Add to Cart</button>

    </div>

          <div class="modal-content">
            <div class="modal-header">

              <h4 class="modal-title" id="myModalLabel">Cart</h4>
            </div>
            <div class="modal-body">

              <table class="table table-hover">
                <thead>
                <tr>
                  <th scope="col">Name</th>
                  <th scope="col">Quantity</th>
                  <th scope="col">Price</th>
                </tr>
                </thead>
                <tr v-for="item in items">
                  <td>{{item.productName}}</td>
                  <td>{{item.producctQty}}</td>
                  <td>{{item.producctUnitPrice}}</td>
                </tr>
                <tr v-show="items.length === 0">
                  <td colspan="4" class="text-center">Cart is empty</td>
                </tr>
                <tr v-show="items.length > 0">
                  <td></td>
                  <td class="text-right">Cart Total</td>
                  <td class="text-right"></td>
                  <td></td>
                </tr>
              </table>

            </div>

          </div>




  </div>

</template>



<script>

  import axios from 'axios';


  export default {
    data() {

      return{
        newProductList:[{
          productId:'',
          productName:''
        }],
        cartItems: [],
        items :[{
          productName:'',
          producctQty:'',
          producctUnitPrice:'',
        }],
        price:''


    }

    },

    methods: {

      listProducts(){

        axios.get("http://localhost:8595/price/prodcut_list")
          .then(response => {
            this.newProductList = response.data;
            alert(newProductList);
          })
          .catch(e => {
            this.errors.push(e)
          })
      },

      keyPressed(event){
        var qty = event.target.value;
        axios.get("http://localhost:8595/price/find_byprod_type/"+qty+"/"+1+"")
          .then(response => {
            this.price = response.data;
            items[0].producctUnitPrice=this.price;
            console.log(this.price)
          })
          .catch(e => {
            this.errors.push(e)
          })
        //this.newSet = JSON.stringify(this.newSet);
        this.$emit('newDataset',this.newSet);
      },


      addToCart(itemToAdd) {
        this.items.push(itemToAdd);

      }

    },
    created: function(){
      this.listProducts();
    }



  }
</script>


<style>

</style>
