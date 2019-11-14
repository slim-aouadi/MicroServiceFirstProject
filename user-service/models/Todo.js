var mongoose=require('mongoose');
var mediaSchema=new mongoose.Schema
({
name:{type:String ,required:true,trim:true},
uploadDate:{type:Date,default:new Date(),required:true}
})

var todoSchema=new mongoose.Schema(
{text:{type:String ,required:true,minglength:1,trim:true},
completed:{type:Boolean,default:false},
completedAt:{type:Number,default:null},
medias:[mediaSchema],
})


module.exports=mongoose.model('Todo',todoSchema);	