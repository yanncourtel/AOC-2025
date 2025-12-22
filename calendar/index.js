// Get current date
const now = new Date();
const currentYear = now.getFullYear();
const currentMonth = now.getMonth(); // 0-indexed (December = 11)
const currentDay = now.getDate();

// Check if we're in December 2025
const isDecember2025 = currentYear === 2025 && currentMonth === 11;

// Determine how many days to unlock
let unlockedDay = 0;
if (isDecember2025 && currentDay <= 25) {
    unlockedDay = currentDay;
} else if (isDecember2025 && currentDay > 25) {
    unlockedDay = 25;
} else if (currentYear > 2025 || (currentYear === 2025 && currentMonth === 11 && currentDay > 25)) {
    // After December 2025, unlock all days
    unlockedDay = 25;
}

// Unlock days progressively
for (let i = 1; i <= unlockedDay; i++) {
    const dayBox = document.getElementById(`day-${i}`);
    if (dayBox) {
        dayBox.classList.remove('locked');
        dayBox.classList.add('unlocked');
    }

    if(i == unlockedDay) {
        dayBox.classList.add('current-day');
    }
}

// Show/hide phase indicators based on unlocked days
const phase1Indicator = document.getElementById('phase-1-indicator');
const portalIndicator = document.getElementById('portal-indicator');
const phase2Indicator = document.getElementById('phase-2-indicator');
const phase3Indicator = document.getElementById('phase-3-indicator');

// Phase 1 (days 1-9) - always visible if any day is unlocked
if (unlockedDay >= 1) {
    phase1Indicator.classList.add('visible');
}

// Portal (day 10) - visible when day 10 or later is unlocked
if (unlockedDay >= 10) {
    portalIndicator.classList.add('visible');
}

// Phase 2 (days 11-20) - visible when day 11 or later is unlocked
if (unlockedDay >= 11) {
    phase2Indicator.classList.add('visible');
}

// Phase 3 (days 21-25) - visible when day 21 or later is unlocked
if (unlockedDay >= 21) {
    phase3Indicator.classList.add('visible');
}

// Prevent clicking on locked days
document.querySelectorAll('.day-box.locked').forEach(box => {
    box.addEventListener('click', (e) => {
        e.preventDefault();
    });
});