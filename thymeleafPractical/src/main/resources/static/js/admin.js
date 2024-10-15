// Generating the QR code
        let qrUrl = 'https://yourdomain.com/join';  // URL where players will join
        let qrcode = new QRCode(document.getElementById("qrcode"), qrUrl);

        // WebSocket for real-time communication with server
        let socket = new WebSocket("ws://localhost:8080/game");

        // When a correct answer is received, display the winner's name and move to next question
        socket.onmessage = function (event) {
            let data = JSON.parse(event.data);
            if (data.type === 'CORRECT_ANSWER') {
                document.getElementById("winner-message").textContent = `Congratulations ${data.playerName}!`;
                // Load the next question
                setTimeout(() => {
                    loadNextQuestion();
                }, 3000);
            }
        };

        // Function to load next question from the server
        function loadNextQuestion() {
            fetch("/api/next-question")
                .then(response => response.json())
                .then(data => {
                    document.getElementById("question-text").textContent = data.question;
                    document.getElementById("winner-message").textContent = "";
                });
        }

        // Load the first question when the page is ready
        window.onload = loadNextQuestion;