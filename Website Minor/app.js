import path from "path";
import express from "express";
import bodyParser from "body-parser";
import pg from "pg";
const app = express();
const port = 3000;

const db = new  pg.Client({
  user : "postgres",
  host : "localhost",
  database : "minor1",
  password : "h7VNCJ@1",
  port : 5432,
});
db.connect();

app.use(bodyParser.urlencoded({ extended: true }));

let headsCount = 0;
let tailsCount = 0;


    function updateCount(result) {
      if (result === 'Heads') {
        headsCount++;
        document.getElementById('headsCount').innerText = headsCount;
      } else {
        tailsCount++;
        document.getElementById('tailsCount').innerText = tailsCount;
      }
    }

    function displayLastFlipResult(result) {
      const coinElement = document.getElementById('coin');
      const lastFlipResultElement = document.getElementById('lastFlipResult');

      coinElement.innerText = result;
      lastFlipResultElement.innerText = `Last Flip: ${result}`;
    }

    function displayLastFlipResults(results) {
      const lastFlipResultElement = document.getElementById('lastFlipResult');
      lastFlipResultElement.innerText = `Last Flip: ${results.join(', ')}`;
    }

    app.get('/', function (req, res) {
        const __dirname = "D:/UPES notes/Semester-5/Minor project 1/Website Minor";
        const options = {
            root: path.join(__dirname)
        };
     
        const fileName = 'index.html';
        res.sendFile(fileName, options, function (err) {
            if (err) {
                next(err);
            }
        });
    });



    app.get("/Our_project",function(req,res){
        const __dirname = "D:/UPES notes/Semester-5/Minor project 1/Website Minor";
        const options = {
            root: path.join(__dirname)
        };
     
        const fileName = 'Our_project.html';
        res.sendFile(fileName, options, function (err) {
            if (err) {
                next(err);
            }
        });
    });

    app.get("/new",async(req,res)=>{
        headsCount = await db.query("SELECT head FROM count");
        tailsCount = await db.query("SELECT tail FROM count");
        res.render("new.ejs",{headsCount : headsCount.rows[0].head,tailsCount : tailsCount.rows[0].tail});
    });

    app.post("/flip1",async(req,res)=>{
        const result = Math.random() < 0.5 ? 'Heads' : 'Tails';
        if(result === "Heads"){
            headsCount = await db.query("SELECT head FROM count");
            await db.query("UPDATE count SET head = "+(headsCount.rows[0].head+1));
        }
        else if(result === "Tails"){
            tailsCount = await db.query("SELECT tail FROM count");
            await db.query("UPDATE count SET tail = "+(tailsCount.rows[0].tail+1));
        }
        res.redirect("/new");
    });

    app.post("/flip10",async(req,res)=>{
      for (let i = 0; i < 10; i++) {
        const result = Math.random() < 0.5 ? 'Heads' : 'Tails';
        if(result === "Heads"){
          headsCount = await db.query("SELECT head FROM count");
          await db.query("UPDATE count SET head = "+(headsCount.rows[0].head+1));
      }
      else if(result === "Tails"){
          tailsCount = await db.query("SELECT tail FROM count");
          await db.query("UPDATE count SET tail = "+(tailsCount.rows[0].tail+1));
      }
      }
      res.redirect("/new");
  });

  app.post("/flip20",async(req,res)=>{
    for (let i = 0; i < 20; i++) {
      const result = Math.random() < 0.5 ? 'Heads' : 'Tails';
      if(result === "Heads"){
        headsCount = await db.query("SELECT head FROM count");
        await db.query("UPDATE count SET head = "+(headsCount.rows[0].head+1));
    }
    else if(result === "Tails"){
        tailsCount = await db.query("SELECT tail FROM count");
        await db.query("UPDATE count SET tail = "+(tailsCount.rows[0].tail+1));
    }
    }
    res.redirect("/new");
});

app.listen(port, () => {
    console.log(`Server running on port ${port}`);
  });