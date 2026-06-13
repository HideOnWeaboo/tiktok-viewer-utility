import time
import random
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from fake_useragent import UserAgent

class TikTokViewerBot:
    """Simulates a TikTok video viewer with realistic behavior."""

    def __init__(self, headless=False):
        self.headless = headless
        self.driver = None
        self.ua = UserAgent()

    def _create_driver(self):
        options = Options()
        if self.headless:
            options.add_argument("--headless")
        options.add_argument(f"user-agent={self.ua.random}")
        options.add_argument("--disable-blink-features=AutomationControlled")
        options.add_experimental_option("excludeSwitches", ["enable-automation"])
        options.add_experimental_option("useAutomationExtension", False)
        self.driver = webdriver.Chrome(options=options)

    def view_video(self, video_url, watch_time=15):
        """Load and watch a TikTok video for specified seconds."""
        self._create_driver()
        try:
            self.driver.get(video_url)
            wait = WebDriverWait(self.driver, 10)
            wait.until(EC.presence_of_element_located((By.TAG_NAME, "video")))
            time.sleep(min(watch_time, 30))
            return True
        except Exception as e:
            print(f"Error viewing video: {e}")
            return False
        finally:
            if self.driver:
                self.driver.quit()

    def view_multiple(self, video_urls, min_delay=2, max_delay=6):
        """View multiple videos with random delays."""
        results = []
        for url in video_urls:
            delay = random.uniform(min_delay, max_delay)
            time.sleep(delay)
            result = self.view_video(url, watch_time=random.randint(5, 20))
            results.append(result)
        return results