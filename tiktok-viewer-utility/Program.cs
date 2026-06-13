using System;
using System.Threading;
using TikTokViewerBot.Core;

namespace TikTokViewerBot
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("TikTok Viewer Utility v1.0");
            Console.Write("Enter TikTok video URL: ");
            string url = Console.ReadLine() ?? "";

            Console.Write("Enter number of views to simulate: ");
            int count = int.Parse(Console.ReadLine() ?? "1");

            using var viewer = new ViewerBot();
            viewer.Run(url, count);

            Console.WriteLine("Done. Press any key to exit.");
            Console.ReadKey();
        }
    }
}