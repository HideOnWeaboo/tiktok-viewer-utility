import pytest
from src.viewer_bot import TikTokViewerBot
from src.utils import extract_video_id, is_valid_tiktok_url

class TestTikTokViewerBot:
    def test_bot_initialization(self):
        bot = TikTokViewerBot(headless=True)
        assert bot.headless == True
        assert bot.driver is None

    def test_view_video_fails_with_bad_url(self):
        bot = TikTokViewerBot(headless=True)
        result = bot.view_video("https://invalid-url.com")
        assert result == False

class TestUtils:
    def test_extract_video_id_standard(self):
        url = "https://www.tiktok.com/@user/video/1234567890123456789"
        assert extract_video_id(url) == "1234567890123456789"

    def test_extract_video_id_short(self):
        url = "https://vm.tiktok.com/ZS1234567/"
        assert extract_video_id(url) is None

    def test_is_valid_tiktok_url_valid(self):
        assert is_valid_tiktok_url("https://www.tiktok.com/@user/video/123") == True

    def test_is_valid_tiktok_url_invalid(self):
        assert is_valid_tiktok_url("https://youtube.com/watch?v=123") == False

    def test_extract_video_id_none(self):
        assert extract_video_id("https://example.com") is None