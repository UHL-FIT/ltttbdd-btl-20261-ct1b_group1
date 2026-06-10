$urls = @(
    "https://images.unsplash.com/photo-1528127269322-539801943592?w=800&q=80",
    "https://images.unsplash.com/photo-1440694997168-8ae4033554c7?w=800&q=80",
    "https://samtenhills.vn/wp-content/uploads/2024/11/kinh-nghiem-du-lich-da-lat-1-minh.jpg?w=800&q=80",
    "https://images.unsplash.com/photo-1725550798518-f551648e4c10?w=800&q=80",
    "https://images.unsplash.com/photo-1583417319070-4a69db38a482?w=800&q=80",
    "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?w=800&q=80",
    "https://images.unsplash.com/photo-1540206395-68808572332f?w=800&q=80",
    "https://images.unsplash.com/photo-1559128010-7c1ad6e1b6a5?w=800&q=80",
    "https://images.unsplash.com/photo-1464822759023-fed622ff2c3b?w=800&q=80",
    "https://images.unsplash.com/photo-1506461883276-594c1257fe52?w=800&q=80",
    "https://images.unsplash.com/photo-1447752809965-926868e4c70a?w=800&q=80",
    "https://images.unsplash.com/photo-1476514525535-07fb3b4ae5f1?w=800&q=80",
    "https://images.unsplash.com/photo-1530122037265-a5f1f91d3b99?w=800&q=80",
    "https://images.unsplash.com/photo-1475924156734-496f6cac6ec1?w=800&q=80",
    "https://images.unsplash.com/photo-1433838550598-a9f6266aca20?w=800&q=80",
    "https://images.unsplash.com/photo-1531737255813-f4bd3db6226c?w=800&q=80",
    "https://images.unsplash.com/photo-1493246507139-91e8fad9978e?w=800&q=80",
    "https://images.unsplash.com/photo-1528493366314-e317cd98ad35?w=800&q=80",
    "https://images.unsplash.com/photo-1535890696-26511a2f9543?w=800&q=80"
)

foreach ($url in $urls) {
    try {
        $result = curl.exe -s -o NUL -w "%{http_code}" $url
        Write-Host "$result - $url"
    } catch {
        Write-Host "ERROR - $url"
    }
}
