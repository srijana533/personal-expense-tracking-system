const ctx = document.getElementById('categoryChart').getContext('2d');

new Chart(ctx, {
  type: 'pie',
  data: {
    labels: ['Food', 'Transport', 'Shopping', 'Health', 'Other'],
    datasets: [{
      data: [4000, 2500, 1500, 800, 1200],
      backgroundColor: ['#ef4444', '#f59e0b', '#3b82f6', '#10b981', '#8b5cf6'],
      borderColor: '#ffffff',
      borderWidth: 2,
    }],
  },
  options: {
    responsive: true,
    plugins: {
      legend: {
        position: 'bottom',
      },
    },
  }


}

);
