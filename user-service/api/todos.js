var express = require('express');
var router = express.Router();
var Todo=require('../models/Todo.js');
var auth = require('../api/auth');

/// ADD A TODO 
router.post('/', function(req, res, next) {

  var todo=new Todo(req.body)

  todo.save(function(err,todo)
  {

    if(err)
      res.send(err);
    else
      res.send(todo);
  })
});
//// ADD MEDIA TO A TODO 
router.post('/:id/media',function(req, res, next) {

 // var todo=new Todo(req.body);
 var todo=Todo.findById(req.params.id).exec((err,todo)=>{
    todo.medias.push(req.body);
      todo.save(function(err,todo){

    if(err)
      res.send(err);
    else
      res.send(todo.medias);
  })
 });


});


router.get('/', auth.authenticate ,function (req, res) {
    Todo.find(function (err , todos) {
        if(err)
            res.send(err);
        if (!todos)
            res.status(404).send();
        else
            res.json(todos);
    });
});


router.get('/:id', function(req, res, next) {
var id=req.params.id;

  Todo.findById(id).exec(function(err,todo){

  	if(err)
  		res.send(err);
  	if(!todo)
  		res.status(400).send();
  	else
  		res.json(todo);
  })
});

/// GET MEDIA FROM TODO ID 
router.get('/:id/medias/:idd', function(req, res, next) {

var idd=req.param('idd');

var meds=[];


 var todo=Todo.findById(req.params.id).exec((err,todo)=>{
    todo.medias.push(req.body);
      todo.save(function(err,todo){

    if(err)
      res.send(err);
    else
      res.send(todo.medias);
  })
 });


});


router.delete('/:id', function(req, res, next) {
var id=req.params.id;

  Todo.findByIdAndRemove(id,function(err,todo){

  	if(err)
  		res.send(err);
  	if(!todo)
  		res.status(400).send();
  	else
  		res.json(todo);
  })
});
module.exports = router;
