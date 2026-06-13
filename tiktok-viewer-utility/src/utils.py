import re
import requests
from bs4 import BeautifulSoup

def extract_video_id(url):
    """Extract TikTok video ID from URL."""
    patterns = [
        r'/video/(\d+)',
        r'/(\d{19})',
        r'v/(\d{19})',
    ]
    for pattern in patterns:
        match = re.search(pattern, url)
        if match:
            return match.group(1)
    return None

def fetch_video_metadata(video_id):
    """Fetch basic metadata about a TikTok video."""
    url = f"https://www.tiktok.com/oembed?url=https://www.tiktok.com/video/{video_id}"
    try:
        response = requests.get(url, timeout=10)
        if response.status_code == 200:
            data = response.json()
            return {
                "author": data.get("author_name", "Unknown"),
                "title": data.get("title", ""),
                "duration": data.get("duration", 0),
            }
    except Exception:
        pass
    return None

def is_valid_tiktok_url(url):
    """Check if a URL is a valid TikTok video URL."""
    pattern = r'https?://(www\.)?(tiktok\.com|vm\.tiktok\.com)/.+'
    return bool(re.match(pattern, url))