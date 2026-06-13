using System;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace TikTokViewerBot.Core
{
    public class ViewerBot : IDisposable
    {
        private readonly IWebDriver _driver;

        public ViewerBot()
        {
            var options = new ChromeOptions();
            options.AddArgument("--headless");
            options.AddArgument("--no-sandbox");
            options.AddArgument("--disable-gpu");
            _driver = new ChromeDriver(options);
        }

        public void Run(string videoUrl, int viewCount)
        {
            for (int i = 0; i < viewCount; i++)
            {
                try
                {
                    _driver.Navigate().GoToUrl(videoUrl);
                    System.Threading.Thread.Sleep(3000); // wait for page load
                    Console.WriteLine($"View {i + 1} / {viewCount} - loaded.");
                }
                catch (Exception ex)
                {
                    Console.WriteLine($"Error on view {i + 1}: {ex.Message}");
                }
            }
        }

        public void Dispose()
        {
            _driver?.Quit();
            _driver?.Dispose();
        }
    }
}