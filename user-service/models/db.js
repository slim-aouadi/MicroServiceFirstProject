var mongoose=require('mongoose');
mongoose.connect('mongodb://mongo:27017/twin',{useNewUrlParser:true}).then(() => console.log("mongo db connected"));


