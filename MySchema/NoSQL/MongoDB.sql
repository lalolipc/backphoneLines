db.states.insertOne({
  {
    id : 1,
    name : "Buenos Aires"
  })

db.cities.InsertOne({
    id : 2,
    name : "Mar del plata",
    prefix : "223",
    province" : {
      name : "Buenos Aires"
    }
})
db.cities.InsertOne({
    id : 1,
    name : "La plata",
    prefix : "221",
    province" : {
      name : "Buenos Aires"
    }
})


db.usertypes.InsertOne({
    id : 1,
    name : "CLIENT"
})
db.usertypes.InsertOne({
    id : 2,
    name : "EMPLOYEE"
})


db.users.InsertOne({
    id : 1,
    name : "Tiziano",
    lastName : "Carrasco",
    userName : "tizicar",
    password : "123",
    dni : "45211121",
    userType : {
      name : "CLIENT"
    },
    city : {
      name : "Mar del Plata"
    },
    active : true
})

db.phonetypes.InsertOne({
    id : 1,
    name : "CELLPHONE"
})

db.phonetypes.InsertOne({
    id : 2,
    name : "RESIDENTIAL"
})

db.phones.InsertOne({
    id : 1,
    number : "2235463801",
    user : {
      	name : "Tiziano",
    	lastName : "Carrasco",
   	userName : "tizicar",
	password : "123",
	dni : "45211121",
	    userType : {
	      name : "CLIENT"
		    },
	    city : {
	      name : "Mar del Plata"
		    },
	 active : true
    }
	city : {
	      name : "Mar del Plata"
		    },
    phoneType:{
	 name : "CELLPHONE",
	}
 active : true
 status: "ENABLED"
})

db.phones.InsertOne({
    id : 1,
    number : "2235463899",
    user : {
      	name : "Tiziano",
    	lastName : "Carrasco",
   	userName : "tizicar",
	password : "123",
	dni : "45211121",
	    userType : {
	      name : "CLIENT"
		    },
	    city : {
	      name : "La Plata"
		    },
	 active : true
    }
	city : {
	      name : "La Plata"
		    },
    phoneType:{
	 name : "CELLPHONE",
	}
 active : true
 status: "ENABLED"
})



db.rates.InsertOne({
    id : 1,
    cityFrom : {
	name : "Mar del plata",
   	 prefix : "223",
   	 province" : {
      		name : "Buenos Aires"
    			}
		}
	cityTo:{
		name : "La plata",
		prefix : "221",
			province" : {
			      name : "Buenos Aires"
				    }
		}
	salePrice:7.37,
	costPrice:4.88
})

db.callss.InsertOne({
	id:1,
	originPhone:{
		number : "2235463801",
    		user : {
      			name : "Tiziano",
    			lastName : "Carrasco",
   			userName : "tizicar",
			password : "123",
			dni : "45211121",
	    			userType : {
	     				 name : "CLIENT"
		    		},
	    		city : {
	      			name : "La Plata"
		    		},
			active : true
  			 }
		city : {
	      		name : "La Plata"
		    	},
   		 phoneType:{
	 		name : "CELLPHONE",
			}
		 active : true
		 status: "ENABLED"
		},
	DestinationPhone:{
		number : "2235463899",
    		user : {
      			name : "Tiziano",
    			lastName : "Carrasco",
   			userName : "tizicar",
			password : "123",
			dni : "45211121",
	    			userType : {
	     				 name : "CLIENT"
		    		},
	    		city : {
	      			name : "La Plata"
		    		},
			active : true
  			 }
		city : {
	      		name : "La Plata"
		    	},
   		 phoneType:{
	 		name : "CELLPHONE",
			}
		 active : true
		 status: "ENABLED"
		},

	rate:{
		cityFrom : {
		name : "Mar del plata",
   		 prefix : "223",
   		 province" : {
      			name : "Buenos Aires"
    				}
			}
		cityTo:{
			name : "La plata",
			prefix : "221",
				province" : {
			    	  name : "Buenos Aires"
					    }
			}
		salePrice:7.37,
		costPrice:4.88},
	dateCall:20-01-01 03:10:10,
	invoice: null,
	duration: 400,
	totalPrice:53.67,
	user:{
		 id : 1,
   		 name : "Tiziano",
  		  lastName : "Carrasco",
   		 userName : "tizicar",
    		password : "123",
    		dni : "45211121",
   			 userType : {
     				 name : "CLIENT"
    					},
   			 city : {
     				 name : "Mar del Plata"
    				},
    		active : true,
	},
	city: {
     		 name : "Mar del Plata"
    		}


})

