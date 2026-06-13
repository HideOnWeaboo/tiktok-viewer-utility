using System;
using Xunit;
using TikTokViewerBot.Core;

namespace TikTokViewerBot.Tests
{
    public class ViewerBotTests
    {
        [Fact]
        public void UserAgentProvider_ReturnsNonEmptyString()
        {
            string ua = UserAgentProvider.GetRandom();
            Assert.False(string.IsNullOrEmpty(ua));
            Assert.Contains("Chrome", ua);
        }

        [Fact]
        public void ViewerBot_Constructor_DoesNotThrow()
        {
            // Note: This test requires ChromeDriver installed.
            // In CI, mock or skip if driver not present.
            try
            {
                using var bot = new ViewerBot();
                Assert.NotNull(bot);
            }
            catch (Exception ex)
            {
                // Driver not available — skip test gracefully
                Assert.Contains("chromedriver", ex.Message, StringComparison.OrdinalIgnoreCase);
            }
        }
    }
}