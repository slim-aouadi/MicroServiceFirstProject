var express = require('express');
var router = express.Router();
var User = require('../models/users');
var mongoose = require('mongoose');
var bcrypt = require('bcrypt-nodejs');
var jwt = require('jsonwebtoken');
var auth = require('../api/auth')

router.post('/register',function (req, res) {
    var user = new User({
        password: bcrypt.hashSync(req.body.password, bcrypt.genSaltSync(10)),
        email: req.body.email
    })
    console.log(user);
    user.save(function (err, todo) {
        if (err)
            res.send(err);
        else
            res.send(user);
    })

});


router.get('/users' ,function (req, res) {
    User.find(function (err , todos) {
        if(err)
            res.send(err);
        if (!todos)
            res.status(404).send();
        else
            res.json(todos);
    });
});

router.post('/login',function (req, res) {

    User.findOne({ email: req.body.email},function (err, user) {
        if (bcrypt.compareSync(req.body.password, user.password)) {
            console.log('user found', user);
            var token = jwt.sign({email: user.email}, 's3cr3t', {expiresIn: 3600});
            res.status(200).json({success: true, token: token});
        } else {
            res.status(401).json('unauthorized');
        }
    });
});



module.exports = router;