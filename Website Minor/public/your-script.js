import pg from "pg";
  // Import Chart.js library
import Chart from "chart.js/auto";

const db = new pg.Client({
  user: "postgres",
  host: "localhost",
  database: "minor1",
  password: "h7VNCJ@1",
  port: 5432,
});
db.connect();

// Query the database for head and tail counts
const queryHeads = db.query("SELECT head FROM count");
const queryTails = db.query("SELECT tail FROM count");

// Handle the asynchronous nature of database queries
Promise.all([queryHeads, queryTails])
  .then((results) => {
    const headsCount = results[0].rows[0].head;
    const tailsCount = results[1].rows[0].tail;

    // Sample data
    const data = {
      labels: ['Head Count', 'Tail Count'],
      datasets: [{
        label: 'Monte Carlo Method',
        data: [headsCount, tailsCount],
        borderColor: 'rgba(75, 192, 192, 1)', // Border color for the line
        borderWidth: 2,                        // Border width for the line
        fill: false                            // Do not fill the area under the line
      }]
    };

    // Configuration options
    const options = {
      scales: {
        y: {
          beginAtZero: true
        }
      }
    };

    // Get the context of the canvas element
    const ctx = document.getElementById('myLineChart').getContext('2d');

    // Create a line chart
    const myLineChart = new Chart(ctx, {
      type: 'line',    // Specify the type of chart (line)
      data: data,
      options: options
    });
  })
  .catch((error) => {
    console.error("Error fetching data:", error);
  });
